import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create("Primer");

        ActorRef primerMaster = system.actorOf(Props.create(PrimerMaster.class, 20));

        // primerMaster.tell("Calling Primer Master", primerMaster);
        primerMaster.tell(new int[] {0, 10000}, primerMaster);

    }
}
