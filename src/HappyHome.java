import java.io.*;

class Home implements Serializable{

    String pet;

    String  petname;
    int carCount;

    String carColor;

    String carModel;

    String bed;


    public void setPet(String pet) {
        this.pet = pet;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }



     public Home(String pet, String petname, int carCount, String carColor, String carModel, String bed){
        this.bed=bed;
        this.pet=pet;
        this.carColor=carColor;
        this.carModel=carModel;
        this.petname=petname;
        this.carCount=carCount;
    }

    public String getPet() {
        return pet;
    }

    public String getPetname() {
        return petname;
    }

    public int getCarCount() {
        return carCount;
    }

    public String getCarColor() {
        return carColor;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getBed() {
        return bed;
    }
    @Override
    public String toString(){
        return "Car : " + carModel +" \nCarcount : " + carCount + "\nPet Name : " + petname;
    }
}



public class HappyHome {
    private static final long serialVersionUID = 1L;
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Home home = new Home("Dog", "Cassie", 40, "red", "lamborgini lisbao", "Double bed");
        serialize(home, "serialize.ser");
        Home deserializedHome = deserialize("serialize.ser");
        System.out.println(deserializedHome);




    }
    private static void serialize(Home home, String file) throws IOException, ClassNotFoundException {
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(home);
        out.close();
        fileOut.close();

    }
    private static Home deserialize(String file) throws IOException, ClassNotFoundException {
        Home myHome = null;
        try{
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            myHome = (Home) in.readObject();
            in.close();
            fileIn.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return myHome;
    }
}
