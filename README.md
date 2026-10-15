**BACKEND del sistema de Mascotas**
Este microservicio desarrollado en Spring Boot entrega funciones de CRUD al sistema, integrando un enfoque tipo RBAC
donde las rutas se pueden acceder únicamente por aquellos consumidores que tengan el rol adecuado. El acceso a este backend 
está limitado por dos "variables", las cuales deben ser incluidas en cada petición para poder consumir los endpoints.

**Acceso**
JWT --> Este componente es emitido por la User Pool de Cognito y validado por nuestro backend, en el flujo regular del sistema este 
se envía mediante el frontend. Este token también entrega el/los rol/es del usuario, lo que se vuelve muy importante para las peticiones
hechas hacia el sistema.

X-Secret-Gateway --> El acceso a nuestro backend se hace mediante una API Gateway de AWS, pero debido a que nuestra instancia EC2 
tiene una IP pública necesitamos una manera extra de "protegerlo", evitar que sea pueda acceder a el desde cualquier parte ya que de 
lo contrario *toda* la seguridad provista por la API sería inútil. Para esto se ocupa el parametro "X-Secret-Gateway" en el header de 
cada petición enviada por la API Gateway, este valor **secreto** es ingresado al backend mediante los secretos de github (para que el 
backend pueda conocer el valor) y en los parametros de la integración de las rutas de la API Gateway ingresamos este valor secreto.

**Acceso por ROLES**

<img width="683" height="227" alt="Captura de pantalla 2026-09-16 a la(s) 10 25 31" src="https://github.com/user-attachments/assets/31d0f2ed-0910-4ed5-901f-57029efd74f1" />

El sistema tiene 3 roles: 'Admin', 'Vet' y 'Owner'. Los endpoints del sistema cubren las funcionalidades del modelo **C.R.U.D**, a continuación 
dejo un desglose de estas operaciones y los roles que pueden ejecutarlas. Debo mencionar que a futuro la función de actualizar se encontrará disponible para 'Admin' y 'Vet'.

**Admin** --> Crear, eliminar y listar todos los registros del sistema.
**Vet** --> Crear nuevos registros y listar todos los que haya creado o estén asociados a él.
**Owner** --> Ver la información de registros particulares de mascotas

**Integración con base de datos**
La integración con la base de datos es una tarea sencilla gracias a los patrones de diseño de *Repository* y *Service*, con los cuales no 
tenemos que preocuparnos sobre los detalles de como se consume e ingresan los datos a la BD (Supabase en este caso). Necesitamos de una 
url de conexión, nombre de usuario y contraseña para establecer la conexión a la BD.


