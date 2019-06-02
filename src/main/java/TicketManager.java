import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TicketManager {

    public static void main(String[] args) {
        Console console = System.console();

        if (console != null) {
            ArrayList<String> tickets = new ArrayList<String>();

            console.printf("Enter command for add ticket or delete, update, show ticket by id or show all tickets: \n");
            String command = console.readLine();

            char[] addCommand = new char[3];
            command.getChars(0, 3, addCommand, 0);
            String comAdd = String.valueOf(addCommand);

            char[] manipulationCommand = new char[6];
            command.getChars(0, 6, manipulationCommand, 0);
            String comMan = String.valueOf(manipulationCommand);

            char[] showCommandById = new char[5];
            command.getChars(0, 5, showCommandById, 0);
            String comShowById = String.valueOf(showCommandById);

            char[] showAllCommand = new char[7];
            command.getChars(0, 7, showAllCommand, 0);
            String comShowAll = String.valueOf(showAllCommand);

            char[] exitCommand = new char[4];
            command.getChars(0,3, exitCommand,0);
            String comExit = String.valueOf(exitCommand);

            char[] helpCommad = new char[4];
            command.getChars(0,3, helpCommad,0);
            String comHelp = String.valueOf(helpCommad);

            if (comAdd.equalsIgnoreCase("add ")) {
                String subCommand = command.substring(4);
                subCommand = subCommand.trim();

                StringTokenizer tokenizer = new StringTokenizer(subCommand, ", ");
                if (tokenizer.countTokens() != 4) {
                    console.printf("\n I do not understand your expression, write 'help' and check command syntax");
                } else {
                    tickets.add(subCommand);
                    Integer getIdForLastTicket = tickets.lastIndexOf(subCommand);

                    try (FileWriter writer = new FileWriter("output.txt", true)) {
                        writer.write(getIdForLastTicket+", "+subCommand+"\n");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            } else if (comMan.equalsIgnoreCase("delete ")) {
                String subCommand = command.substring(7);
                subCommand = subCommand.trim();

                StringTokenizer tokenizer = new StringTokenizer(subCommand, ", ");
                if (tokenizer.countTokens() != 5) {
                    console.printf("\n I do not understand your expression, write 'help' and check command syntax");
                } else {
                    Integer getTicketId = Integer.valueOf(tokenizer.nextToken());
                    tickets.remove(getTicketId);

                    try (FileWriter writer = new FileWriter("output.txt", true)){
                        writer.write(subCommand+"\n");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            } else if (comMan.equalsIgnoreCase("update ")) {
                String subCommand = command.substring(7);
                subCommand = subCommand.trim();

                StringTokenizer tokenizer = new StringTokenizer(subCommand, ", ");
                if (tokenizer.countTokens() != 5) {
                    console.printf("\n I do not understand your expression, write 'help' and check command syntax");
                } else {
                    Integer getTicketId = Integer.valueOf(tokenizer.nextToken());
                    tickets.set(getTicketId, subCommand);

                    try (FileWriter writer = new FileWriter("output.txt", true)){
                        writer.write(subCommand+"\n");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            } else if (comShowById.equalsIgnoreCase("show ")) {
                String subCommand = command.substring(6);
                subCommand = subCommand.trim();

                StringTokenizer tokenizer = new StringTokenizer(subCommand, ", ");
                if (tokenizer.countTokens() != 5) {
                    console.printf("\n I do not understand your expression, write 'help' and check command syntax");
                } else {
                    Integer getTicketId = Integer.valueOf(tokenizer.nextToken());
                    console.printf(tickets.get(getTicketId)+"\n");

                    try (FileWriter writer = new FileWriter("output.txt", true)){
                        writer.write(subCommand+"\n");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            } else if (comShowAll.equalsIgnoreCase("showAll")) {
                Object[] ticketArray = tickets.toArray();
                for (Object ticket : ticketArray) {
                    String currentTicket = ticket.toString();
                    Integer getTicketId = Integer.valueOf(tickets.indexOf(currentTicket));
                    console.printf(getTicketId+", "+currentTicket+"\n");

                    try (FileWriter writer = new FileWriter("output.txt", true)){
                        writer.write(getTicketId+", "+currentTicket+"\n");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            } else if (comExit.equalsIgnoreCase("exit")) {
                System.exit(1);
            } else if (comHelp.equalsIgnoreCase("help")) {
                console.printf("I understand this commands: \n");
                console.printf("command 'add ' and expression 'to, flight, surname f.p., date' \n");
                console.printf("command 'delete ' and expression 'id, to, flight, surname f.p., date' \n");
                console.printf("command 'update ' and expression 'id, to, flight, surname f.p., date' \n");
                console.printf("command 'show ' and expression 'id, to, flight, surname f.p., date' \n");
                console.printf("command 'showAll'\n");
                console.printf("command 'exit'\n");
            } else {
                console.printf("\n I do not understand you, write 'help' and check command syntax");
            }
        }
    }
}
