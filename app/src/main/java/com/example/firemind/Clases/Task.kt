import java.util.Calendar

class Task(
    var nameTask: String,
    var description: String,
    var initDate: Calendar,
    var endDate: Calendar,
    var finish: Boolean,
    var subTask: Boolean,
    var listSubTask: ArrayList<Task>
) {
    lateinit var id : String
    fun complete() {
        if (subTask) {
            var complete: Int = 0
            for (i in listSubTask) {
                if (i.finish) {
                    complete++
                }
                if (complete == listSubTask.size) {
                    finish = true
                }
            }
        }
    }
}
