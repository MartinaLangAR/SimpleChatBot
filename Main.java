
class Main{
    public static void main(String[] args){
        try{
        Client cli = new Client();
        cli.est_con();
        ChatGUI gui = new ChatGUI();
        //cli.close_con();
        }
        catch(Exception e){
            System.out.println("Unknown Host");
        }
    }

}