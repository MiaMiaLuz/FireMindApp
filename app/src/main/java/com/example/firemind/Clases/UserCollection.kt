
import java.util.Date
import java.security.Permission

public class UserCollection{

    var id_user : Int = 0
    lateinit var name : String
    lateinit var email : String
    lateinit var pass : String
    var biometric : Boolean = false
    lateinit var icon : String
    lateinit var birthday : Date

    lateinit var permissions : ArrayList <Permission>
    lateinit var tasks : ArrayList <Task>
    lateinit var likeList : Map<String, ArrayList<Liked>>
    lateinit var friendList : ArrayList<UserCollection>
    lateinit var account : Account

    fun estParam (name : String, icon : String, birthday : Date, permissions : ArrayList<Permission>, account : Account){
        this.name = name
        this.icon = icon
        this.birthday = birthday
        this.permissions.addAll(permissions)
        this.account = account
    }
    constructor(email: String, pass: String, biometric: Boolean) {
        this.email = email
        this.pass = pass
        this.biometric = biometric
    }

    override fun toString():String{
        return "Usuario : $name"
    }
}