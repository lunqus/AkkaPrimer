import akka.actor.AbstractActor;

public class PrimerWorker extends AbstractActor {

    int id;

    public PrimerWorker(int id) {
        this.id = id;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(this::onReceive)
                .build();
    }

    private void onReceive(Object message) {
        System.out.println("Got message in worker " + id + " message: " + message);
    }
}
