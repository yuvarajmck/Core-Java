import java.io.Serializable;
        import java.io.FileOutputStream;
        import java.io.FileInputStream;
        import java.io.ObjectOutputStream;
        import java.io.ObjectInputStream;
        import java.io.IOException;

 class IntegerSerializationExample {
    public static void main(String[] args) {
        int number = 42;

        // Serialization
        serializeInteger(number, "integer.ser");
        System.out.println("Integer serialized successfully.");

        // Deserialization
        int deserializedNumber = deserializeInteger("integer.ser");
        System.out.println("Deserialized Integer: " + deserializedNumber);
    }

    private static void serializeInteger(int value, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(value);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int deserializeInteger(String filePath) {
        int deserializedValue = 0;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deserializedValue = (int) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedValue;
    }
}


