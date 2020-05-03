import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;
import java.util.List;

public class PrimerMaster extends AbstractActor {

    private int numOfRoutees;
    private Router workerRouter;
    private List<Routee> routees = new ArrayList<Routee>();

    public PrimerMaster(int divide) {

        numOfRoutees = divide;
        System.out.println("Number of chunks: " + divide);

        for (int i = 0; i < divide; i++) {
            ActorRef workerActor = getContext().actorOf(Props.create(PrimerWorker.class, i));
            routees.add(new ActorRefRoutee(workerActor));
        }

        workerRouter = new Router(new RoundRobinRoutingLogic(), routees);

    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(this::onReceive)
                .build();
    }

    private void onReceive(Object message) {
        System.out.println("Called Primer Master, " + message);

        if(message instanceof  int[]) {
            int[] range = (int[]) message;

            var from = range[0];
            var to = range[1];

            System.out.println("From: " + from + " To: " + to);

            workerRouter.route("Hello Worker", getSelf());
        }
    }
}
