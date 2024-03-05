INTEGRANTES: Bernal Mateo, Chaluisa Silvia, Erazo Emilio, Pinzon Marcelo </b>
LINK DE VIDEO: https://www.youtube.com/watch?v=NECFfIxWQPg 
Tema: Sistema de gestión de para una Tienda Automotriz
1) La distribucion de las carpetas y pantallas se las distribuye de la siguiente manera:
   ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/133398724/b509a1e5-1800-4319-af0e-06e36d25bb33)

2) Pantala de inicio de secion *LogIn* creada_<br>
![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/133398724/f673699e-c569-4c43-a35b-2893a9ba873d)

3) *Boton ingresar*: Permite al usuario ingresar al sistema con las credenciales correctas<br>
<b>Credenciales correctas (Que existen en la base de datos)</b>
![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150528715/b9452d97-dd48-4d2e-a7e5-517fec4f56f0)<br>
<b>Credenciales incorrectas (No existen registros de ese usuario en la base de datos)</b>
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150528715/f8818632-f05d-40ca-a061-d6d594a141f2)<br>
4) Boton de salir: permite cerrar el programa y muestra un boton con un mensaje de despedida.
  ![img.png](img.png) ![img_1.png](img_1.png)
5) Ingreso a la pantalla cajero, ingresando las credenciales (usuario: usuario2, password:user2), si las credenciales son correctas permiten el ingreso a la siguiente ventana.
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/133398724/29cbe027-a705-4f2b-ade0-a7568eed6f2b)
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/133398724/20b4365e-4a68-4a69-b892-fb886d6b238a)
6) Visualización una vez se ingresado a la pantalla de cajero<br>
   a) La pantalla cajero se diseño con pestañas que fueron realizadas con JTabbedPane <br>
   b) Los datos del vendedor son extraidos de la base de datos<br>
   c) 
   ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/133398724/99ed67d4-849e-4c55-82b3-eb94015d9f80)
7) Para las selecciones del producto a comprar de igual manera son datos que se extraen de una conexion a la base de datos MySQL<br>
   a) La cantidad a elegir y seleccionar productos estan hechos con Combobox<br>
   b)Para elegir la cantidad se uso un Jspinner <br>
   ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/133398724/fff86374-8484-4089-b383-50fb273c871e)

8) Seleccion de Productos<br>
  a) Una vez seleccionada la imagen se visualizar la imagen del producto a elegir y de igual manera se podra elegir la cantidad que se desee comprar<br>
  b) En la parte del total se realiza un suma automatica de la cantidad del producto que se desee eligir.<br>
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/133398724/c885b336-ee7e-43af-b691-8c67811b40c6)

   
9) En caso de no ser llenados los campos del cliente el sistema no permitira procesar la compra
   ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/133398724/73d1b25b-bead-4c3d-b147-d5ba421ef515)

10) Para crear la factura se hace uso de la libreia iText, esta nos permite crear diferentes tipos de archivos, en el caso deñ rpoyecto PDFs.
   ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/8a8cd9c3-3c3e-4120-bae7-48c62fa98225)

11) Los PDFs se guardan autoamticamente en la carpeta PDF_generado, con el numero de cedula.
    ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/0c969e02-140a-44c9-afbe-07f2a7b390fb)

12) Al llenar correctamente los campos del cliente permite procesar la compra y la impresión de la factura
    ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/133398724/09c104d2-38ec-4bf1-acc1-de7c10d74197)
13) La impresion de la factura se procesa y se lo encuentra en la carpeta señalada
   ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/133398724/57a09a7d-4872-4c53-b78f-0c3347368b5b)
14) Una vez teniendo acceso de la pantalla de inicio
   [![Imagen1.png](https://i.postimg.cc/KzS3q128/Imagen1.png)](https://postimg.cc/8F4CsPc2)

15)Accedemos a la pantalla de administrador, donde podemos seleccionar una de las ventanas
   [![Imagen2.png](https://i.postimg.cc/QNnjkHrL/Imagen2.png)](https://postimg.cc/V5nyzs64)

16)Para la ventana de Productos en Stock se puede seleccionar diversos productos, adicional la impresion del mismo sera en una JTable
   [![Imagen3.png](https://i.postimg.cc/x8z2s3KX/Imagen3.png)](https://postimg.cc/tnpwJPvb)
   [![Imagen4.png](https://i.postimg.cc/PfpvsGyw/Imagen4.png)](https://postimg.cc/c6WLwbVx)

17) Para la pestaña de visualizar cajero podemos escoger  uno de las opciones dadas y con la misma porder visualizar las ventas realizadas
   [![Imagen5.png](https://i.postimg.cc/XqGHSV7J/Imagen5.png)](https://postimg.cc/7J4MSrz8)

18) Para la pestaña de agregar cajero el Administrador podra ingresar un nuevo cajero con los datos solicitados
   [![Imagen6.png](https://i.postimg.cc/JhdJZjMy/Imagen6.png)](https://postimg.cc/R3KNz639)

19) Para la pestaña de agrgar producto el Administrador podra añadir nuevos productos a la lista de la mano de una imagen
   [![Imagen8.png](https://i.postimg.cc/yN5gkByc/Imagen8.png)](https://postimg.cc/KKPvH6V8)





