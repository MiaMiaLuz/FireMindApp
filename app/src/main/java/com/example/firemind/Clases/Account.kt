
import java.util.Date
import java.security.Permission
class Account{
    var accountID : Int = 0
    var baseSalary : Float = 0f
    var manualDeposit : Boolean = true
    lateinit var lastDeposit : Date
    var extras = HashMap <String, Float>()
    var spents = HashMap <String, Float>()
    
    private fun positive(): Float{
        var total = 0f
        var it = extras.iterator()
        while (it.hasNext()){
            total += it.next() as Float
        }
        return total + baseSalary
    }

    private fun negative():Float{
        var total : Float = 0f
        var it = spents.iterator()
        while (it.hasNext()){
            total += it.next() as Float
        }
        return total
    }

    private fun balance(): Float{
        return positive() - negative()
    }
} 