import akka.actor.AbstractActor;

public class PrimerMaster extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(this::onReceive)
                .build();
    }

    private void onReceive(Object message) {
        System.out.println("Called Primer Master, " + message);
    }
}
