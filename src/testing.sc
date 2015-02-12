val p = new Processor

val A = new Agents(p)

val admin1 = new Admin(p)

admin1.rollbackByAdmin()

A.edit(10)

admin1.commitByAdmin()

admin1.rollbackByAdmin()

p.commit()
A.edit(20)

p.rollback()
p.commit()
