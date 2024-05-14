package asaas.utils

abstract class BaseEntity {
   Date dateCreated
   Date lastUpdated
   Boolean deleted = false

  static constraints = {
    }

    static mapping = {
        tablePerHierarchy false
    }   
}