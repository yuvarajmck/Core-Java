
class Reservation {


    private static int ticketCount=1;

    static synchronized void getTicket(){
        if(ticketCount!=0) {
            ticketCount--;
            System.out.println("Ticket booked Successfully");
        }
        else if(ticketCount==0){
            System.out.println("Ticket not available");
        }
    }

    static void cancelTicket(){
        ticketCount++;
        System.out.println(ticketCount);
    }
}

class  Consumer extends Thread{

    @Override
    public void run() {
        Reservation.getTicket();
    }
}
public class TrainReservation  extends  Thread{

    public static void main(String[] args) throws InterruptedException {


        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();
        consumer1.start();
        consumer2.start();

        consumer1.join();
        consumer2.join();
       try{
            Thread.sleep(1000);

        }catch (Exception e){

        }
    }
}
