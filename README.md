Proyecto HATEOAS Demo – Clase 05

El motor de HATEOAS hace que la API piense y le diga al usuario que puede hacer ahora sin que el usuario tenga que saberlo de antemano.

Lo que hice fue crear una API en Java con Spring Boot que maneja el recurso llamado Order (orden de compra).
A cada orden le agregué un motor de HATEOAS que significa que la API no solo muestra los datos sino también que acciones se pueden hacer según el estado de esa orden.

Si la orden esta pendiente, la API muestra los enlaces para pagar o cancelar.

Si ya está pagada, muestra seguimiento o reembolso.

Y si está cancelada, solo deja verla o volver a la lista.


Requisitos para ejecutarlo en otra PC

Paso 1: debe tener instalado **JDK 17** o superior

Paso 2: debe tener instalado **Maven** (se incluye con VS Code o IntelliJ).  

Paso 3: Instalar **VS Code**, **IntelliJ** o **NetBeans** (para mi el mas facil es VS Code).  
   - Si usa VS Code, instala estas extensiones:
     - *Extension Pack for Java* (Microsoft)
     - *Maven for Java* (Microsoft)
     - *Spring Boot Extension Pack* (VMware)


Para usarlo debe clonar el repositorio:

En el Bash debe escribir esto:

git clone https://github.com/TU_USUARIO/hateoasdemo.git

cd hateoasdemo

Luego ejecuta la aplicacion (RUN)

Y luego en el navegador puede probar los endpoints:

http://localhost:8080/api/orders/1

http://localhost:8080/api/orders/2

http://localhost:8080/api/orders/3


/1	"status": "Pending"	"pay" y "cancel"

/2	"status": "Paid"	"track_shipment" y "refund"

/3	"status": "Cancelled"	Solo "self" y "list_all"









