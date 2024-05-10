package gitlet;

import static gitlet.Repository.checkInitialized;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ...
     */
    public static void main(String[] args){


        if (args.length==0){
            System.out.println("Please enter a command.");
            System.exit(0);
        }

        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                validOperand(args,1);
                Repository.init();
                break;
            case "add":
                checkInitialized();
                validOperand(args,2);
                Repository.add(args[1]);
                break;
            case "commit":
                checkInitialized();
                validOperand(args,2);
                Repository.commit(args[1]);
                break;
            case "rm":
                checkInitialized();
                validOperand(args,2);
                Repository.rm(args[1]);
                break;
            case "log":
                checkInitialized();
                validOperand(args,1);
                Repository.log();
                break;
            case "global-log":
                checkInitialized();
                validOperand(args,1);
                Repository.globalLog();
                break;
            case "find":
                checkInitialized();
                validOperand(args,2);
                Repository.find(args[1]);
                break;
            case "status":
                checkInitialized();
                validOperand(args,1);
                Repository.status();
                break;
            case "checkout":
                //-- filename
                //[commit id] -- [file name]
                //branchName
                checkInitialized();
                Repository.checkout(args);
                break;
            case "branch":
                validOperand(args,2);
                Repository.branch(args[1]);
                break;
            case "rm-branch":
                validOperand(args,2);
                Repository.removeBranch(args[1]);
                break;
            case "reset":
                //commit id
                validOperand(args,2);
                Repository.reset(args[1]);
                break;
            case "merge":
                validOperand(args,2);
                Repository.merge(args[1]);
                break;

            default:
                System.out.println("No command with that name exists.");
                System.exit(0);
        }
    }

    /** check if operand is valid
     *
     * @param args
     * @param num
     */
    public static void validOperand(String[] args,int num){
        if(args.length!=num){
            throw new RuntimeException(
                    String.format("Incorrect operands."));
        }

    }
}