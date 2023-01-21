// На вход поступает команда стандартизированного типа следующего формата:
// decryption; the arguments are: -key 5 -data "\jqhtrj%yt%m~ujwxpnqq&" -mode dec
// или
// encryption; the arguments are: -mode enc -key 5 -data "Welcome to hyperskill!"
// Наша задача - извлечь ключ Int, сообщение в кавычках String и тип команды enc или dec.

var encryption = false
var decryption = false
var key = 0
var message: String? = ""

fun main() {

    var yourChoice = "enc"

    // Т.к. мы получаем команду в стандартизированном формате, то мы можем создать переменную,
    // которая будет запрашивать пользовательский ввод с клавиатуры и конвертировать ввод
    // в множество. Стандартные фразы для ввода перечислены среди delimiters.
    // Такой подход позволил нам очистить команду от информационного мусора.

    val beyondTheWall =
        readLine()!!.split("encryption; ", "decryption; ", "the arguments are: ", "-key ", "-mode ", "-data ")
            .map { it }.toTypedArray()

    var list = mutableListOf<String>()
    var list2 = mutableListOf<String>()

    var index = 0
    for (i in beyondTheWall) {
        //print(i)
        if (i.length > 0) {
            list.add(i)
        }
    }

    var forWordFinding = list.joinToString()


    val wordFinder = "\"([^\"]*)\"".toRegex()
    message = wordFinder.find(forWordFinding)?.groupValues?.getOrNull(1)

    if ((list.toString()).contains(yourChoice)){
        encryption = true
    } else {
        decryption = true
    }

    var stringForFindingKeyAndWord = list.joinToString()

    // Следующий паттерн во входящей строке перебирает каждый символ,
    // и если этот символ не является числом от 0 до 9 (сам паттерн оформлен следующим образом: [^0-9]),
    // то этот символ заменяется при помощи команды replace пустотой "".
    // В итоге в строке остаются только цифры, которые мы можем конвертировать в Int.

    var num = stringForFindingKeyAndWord.replace(Regex("[^0-9]"), "")
    key = num.toInt()

    if (encryption == true){
        println("Command type: encryption")
    } else{
        println("Command type: decryption")
    }
    println("Your key is: $key")
    println("You message is: $message")

}