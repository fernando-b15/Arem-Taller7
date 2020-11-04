# Arem-Taller7
Este laboratorio fue heccho con el fin de barcar los conceptos del servici de autoescalamiento de instancias ec2 para mejorar rendimineto y por medio de un balanceador de carags recibir peticiones y en caso de necesitar mas cpu se cera una nueva instancia ec2 con el servicio que tienen todas las intancias que ya estaban activas hasat el momento,En este caso se desarrollo un aplicacion web que calcula el producto intreno de dos matrices y retorna en un json de respuesta la resultado o la matriz resultante del producto intreno de dos matrices m1 y m2

# Despligue

  * [![CircleCI](https://circleci.com/gh/fernando-b15/Arem-Taller7.svg?style=svg&circle-token=949cace400f254fdd6e4aedb04b858a837217b14)](https://app.circleci.com/pipelines/github/fernando-b15/Arem-Taller7/1/workflows/525beb91-a0b8-4f98-8501-8095169fa5bc/jobs/1)

# Pre-Requisitos

Para el uso de la aplicacion se requiere que el computador tenga instalados los siguientes prerequisitos:

   * Java 8
   * Maven
   * Git
   * Docker
   * Newman
# Imagen DockerHub
  * [Calculadora Producto Interno](https://hub.docker.com/repository/docker/fernando15/calculadoraproductointerno)
  
# Despliegue en AWS EC2
Primeramente despues de crear nuestra instanci Ec2 e instalar docker vemos que no haya ningun container hasta el momento
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test1.PNG);
Ahora vamos a descargar la imagen de nuestra aplicacion de dockerhub
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test2.PNG);
Ahora verificamos que efectivamente el container con la aplicacion fue desplagado por el puerto 8001
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test3.PNG);
Procedemos a realizar una prueba desde  el buscador con la direccion de la instnaci Ec2
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test4.PNG);
Vemos que efectivamente la calculadora de matrices funciona satisfactoriamente
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test5.PNG);
Ahora desde el archivo rc.local vamos a configurar que el demon de docker se inicie cada vez que inicie la maquina y ademas que inicie un nuevo servicio llamado productointerno que definieremos mas adelante  
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test6.PNG);
Ahora creamos el servicio producto interno par que inicie el docker del servicio producto interno de matrices por el puerto 8001 cad vez que inicie la maquina
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test7.PNG);
Ahora vamos a proceder a crear la AMI O imagen de la maquina donde ya configuramos el servicio de producto interno 
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test8.PNG);
Procedemos adrale un nombre a la AMI y una breve descripcion y la creamos
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test9.PNG);
Ahora vamos a crear un plantilla de lanzamiento de instancia Ec2
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test10.PNG);
Le asiganemos a la plantilla un nombre y una breve descripcion
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test11.PNG);
Ahora vamos a seleccionar la AMI dela instanci Ec2 que creamos previamente,elegimos el tipo de intancia y un par de llaves ssh
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test12.PNG);
Ahora elegimos un Security Group y dejamos la configuracion de red por defecto que viene
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test15.PNG);
Ya podemos ver en el panel de plantillas que efectivamente hemos creado una nueva
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test14.PNG);
Ahora vamos acrear un grupo de auto escalameiento de instacias Ec2
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test16.PNG);
Primero vamos a definir un nombre par el grupo y elgimos la plantilla de lanzamiento de instancias Ec2 que creamos anteriormente para crear nuevas instancias Ec2 con el servcio de producto interno cada vez que se requiera
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test17.PNG);
Ahora vamos a dejar la configuracion de red que viene por defecto y escogemos 3 subredes para mayor disponibilidad
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test18.PNG);
Ahora vamos a elegir un balanceador de carga clasico que sera el encargado de la rececion de peticiones
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test19.PNG);
Configuramos las reglas de entrda del balanceador
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test20.PNG);
Configuramos el Healt Check del balanceador 
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test21.PNG);
Ahora  procedemos a crear el balanceador
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test22.PNG);
Ahora ue creamos el balanceador seleccionamos la opcion de balanaceador clasico y elegimos el balancedor que acabamos de crear
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test23.PNG);
Ahora definimos el tamaño del grupo de escalado definiendo que minimo sera de una instancia y maximo de 5 instancias
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test24.PNG);
Ahora vamos a definir la politica de escalado por consumo de cpu y defenimso el tope de procesamiento en 75 % para que una vez que una instancia que este activa ocupe mas del 75 % de su cpu automaticamente se  despligue  una nueva instancia Ec2 con el servicio de producto interno
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test25.PNG);
Ahora procdemos a crear la el grupo de auto escalamiento
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test26.PNG);
Observamos algunos detalles del grupo que acabamos de crear
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test27.PNG);
Vemos que inicialmente solo tenemos una instancia Ec2 que es el minimo que definimos en la configuracion del grupo de auto escalamiento
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test30.PNG);
Ahora con ayuda de newman desde la carpeta postamn vamos a enviar 500 peticiones simulteneamente a la unica instancia hasta el momento
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test31.PNG);
Ahora por ultimo podemos ver que con la recepcion de 500 peticiones para calcular el producto interno de dos matrices se supero el 75% de procesamiento de la instancia y se desplego una nueva instancia con el servicio de producto interno de dos matrices
![i1](https://github.com/fernando-b15/Arem-Taller7/blob/master/img/test28.PNG);

# Documentacion

Para acceder a la documentacion de la calculadoar del producto interno de matrices ,ingrese al siguiente enlace [apidocs](https://github.com/fernando-b15/Arem-Taller7/blob/master/apidocs) 

# Licencia

La aplicacion cuenta con la siguiente [MIT LICENCE](https://github.com/fernando-b15/Arem-Taller7/blob/master/LICENSE) 

# Autor

   * [Fernanado Barrera Barrera](https://github.com/fernando-b15) :guitar:
