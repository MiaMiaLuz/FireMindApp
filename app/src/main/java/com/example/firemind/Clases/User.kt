
import java.util.Date
import java.security.Permission
import Account
import Task
import Liked

public class User{

    var id_user : Int = 0
    var name : String
    var icon : String
    var birthday : Date

    lateinit var permissions : ArrayList <Permission>
    lateinit var tasks : ArrayList <Task>
    lateinit var likeList : Map<String, ArrayList<Liked>>
    lateinit var friendList : ArrayList<User>
    var account : Account

    constructor(name : String, icon : String, birthday : Date, permissions : ArrayList<Permission>, account : Account){
        this.name = name
        this.icon = icon
        this.birthday = birthday
        this.permissions.addAll(permissions)
        this.account = account
    }

    override fun toString():String{
        return "Usuario : $name"
    }
}