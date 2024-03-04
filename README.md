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
6) Visualización una vez se ingresado a la pantalla de cajero
   a) La pantalla cajero se diseño con pestañas que fueron realizadas con JTabbedPane
   b) Los datos del vendedor son extraidos de la base de datos
   c) 
   ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/133398724/99ed67d4-849e-4c55-82b3-eb94015d9f80)
7) Para las selecciones del producto a comprar de igual manera son datos que se extraen de una conexion a la base de datos MySQL
a) La cantidad a elegir y seleccionar productos estan hechos con Combobox
b)Para elegir la cantidad se uso un 
   ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/133398724/fff86374-8484-4089-b383-50fb273c871e)

-------------------------------------------------------------------------------------------
                                      --- FACTURA ---
* Para poder crear las facturas se hizo uso de la libreria iText, esta libreria nos permite crear diferentes tipos de documentos
  en el caso del proyecto creara PDFs.
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/2cd266a7-1e3d-4ba2-aa96-3a88b5c3cfe7)

* Se creo una clase con nombre FACTURA que permite crearlas.
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/52e59beb-fd51-4f3f-ab4f-443863b215cc)

* La clase tiene las siguintes variables que agregaran a la plantilla.
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/bff091c1-72ed-4a93-87b6-b1369f0f1b40)

* Los PFDs que se crean se guardan automaticamente en una carpeta que tiene como nombre "PDF_generado", los pdfs se gusrdan por
  numero de cedula, ya guardar por nombre causaba un error con los espacion.
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/072bfdd7-0aac-4e24-ab76-e3806a97d597)

* En la siguiente parte del codigo el documeto puede ser editado y agregar diferente contenido, una linea de codigo nos permite definir el tamaño de la     letra y el tipo de letra, otra linea nos permite agregar una imagen al documento en este caso el logo de la empresa.
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/f797a4d1-101a-4dc2-8f4e-a90e100a890f)

* Estos son los elementos que se agregaran al documeto, estos variable son extraidas de la pantalla de cajero.
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/e7a00375-6ae0-412a-a7f7-fe5f25985da1)
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/950ed384-1c06-4802-9644-702b5152a5f1)

* La parte de las facturas esta validada, no se podran crear si faltan datos.
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/185f6713-fbf2-4b5c-b764-4db0f63cccb2)

* Una vez ingresado todops los datos la factura se generara y guardara automaticamente.
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/0676719b-471e-42b2-a716-1fd55366ea1f)
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/f2e04588-256d-487a-a696-f077ae7960d1)
  ![image](https://github.com/Alejo-P/Proyecto-final-POO/assets/150805766/db977c18-617c-4401-91c2-884e7ede7bdb)




