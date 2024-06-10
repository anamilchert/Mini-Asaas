package asaas

class ExpiredPaymentJob {
    static triggers = {
      simple repeatInterval: 5000l // execute job once in 5 seconds
    }

    def execute() {
        println "Job run!"
    }
}
