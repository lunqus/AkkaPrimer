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

        if(message instanceof  int[]) {
            int[] range = (int[]) message;

            var from = range[0];
            var to = range[1];

            System.out.println("From: " + from + " To: " + to);


        }
    }
}
