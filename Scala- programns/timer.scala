object Timer {
    def oncePerSecond(callback: () => Unit ) {
        while (true) { callback(); Thread sleep 1000 }
    }
    def main(args: Array[String]) {
    // () => reṕresenta uma função anonima
     oncePerSecond(() => 
     println("o tempo corre como um raio..."))
    }
}