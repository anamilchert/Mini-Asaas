package asaas.utils

abstract class BaseEntity {
   Date dateCreated
   Date lastUpdated
   Boolean deleted = false

  static constraints = {
        dateCreated nullable: false
        lastUpdated nullable: false
        deleted nullable: false
    }

    static mapping = {
        tablePerHierarchy false
    }   
}