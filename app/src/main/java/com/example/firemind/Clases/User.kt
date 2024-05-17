
import java.security.Permission
import java.util.Date

public class User{

    var id_user : Int = 0
    lateinit var name : String
    var email : String
    var pass : String
    var biometric : Boolean = false
    lateinit var icon : String
    lateinit var birthday : Date

    lateinit var permissions : ArrayList <Permission>
    lateinit var tasks : ArrayList <Task>
    lateinit var likeList : Map<String, ArrayList<Liked>>
    lateinit var friendList : ArrayList<User>
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

    constructor(
        id_user: Int,
        name: String,
        email: String,
        pass: String,
        biometric: Boolean,
        icon: String,
        birthday: Date,
        permissions: ArrayList<Permission>,
        tasks: ArrayList<Task>,
        likeList: Map<String, ArrayList<Liked>>,
        friendList: ArrayList<User>,
        account: Account,
    ) {
        this.id_user = id_user
        this.name = name
        this.email = email
        this.pass = pass
        this.biometric = biometric
        this.icon = icon
        this.birthday = birthday
        this.permissions = permissions
        this.tasks = tasks
        this.likeList = likeList
        this.friendList = friendList
        this.account = account
    }
    constructor(
        id_user: Int,
        name: String,
        email: String,
        pass: String,
        biometric: Boolean,
        icon: String,
        birthday: Date,
    ) {
        this.id_user = id_user
        this.name = name
        this.email = email
        this.pass = pass
        this.biometric = biometric
        this.icon = icon
        this.birthday = birthday
    }


    override fun toString():String{
        return "Usuario : $name"
    }
}