import akka.actor.AbstractActor;

public class PrimerWorker extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(this::onReceive)
                .build();
    }

    private void onReceive(Object message) {
        System.out.println("Got message in worker " + message);
    }
}
