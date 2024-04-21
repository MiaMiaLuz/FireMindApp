
import java.util.Calendar

public class Task(){

    var idTask : Int = 0
    lateinit var nameTask : String
    lateinit var description : String
    lateinit var initDate : Calendar
    lateinit var endDate : Calendar
    var finish : Boolean = false
    var subTask : Boolean = false
    lateinit var listSubTask : ArrayList<Task>

    public fun complete(){
        if(subTask){
            var complete : Int = 0
            for(i in listSubTask){
                if(i.finish){
                    complete ++
                }
                else if (complete == listSubTask.size){
                    finish = true
                }
            }
        }
    }
}