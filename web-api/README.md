# JPA config

## OneToMany

-  @OneToMany annotation has two attributes that we are using. The cascade
attribute defines how cascading affects the entities. 

- ALL means that if the owner is deleted, the cars linked to that owner are
deleted as well.

- The mappedBy="owner" attribute setting tells us that the Car
class has the owner field - which is the foreign key for this relationship.

## ManyToMany

- An owner can have multiple cars and a car can have multiple
owners, you should use the @ManyToMany annotation. 

- In a many-to-many relationship, it is
recommended using Set instead of List with hibernate

```
@ManyToMany(cascade = CascadeType.MERGE)
@JoinTable(name = "car_owner", joinColumns = { @JoinColumn(name = "ownerid") }, inverseJoinColumns = { @JoinColumn(name = "id") })
private Set<Car> cars = new HashSet<Car>(0);
public Set<Car> getCars() {
    return cars;
} 
public void setCars(Set<Car> cars) {
    this.cars = cars;
}
```
- there will be a new join table that is created between the car and owner tables. The join table is defined by using the
@JoinTable annotation

