# libPergamum

Uma biblioteca pessoal em desenvolvimento, criada para acessar o [Sistema Pergamum](http://bibweb.unilab.edu.br) e o [Sistema SIGAA](https://sig.unilab.edu.br) da [Universidade da Integração Internacional da Lusofonia Afro-Brasileira (UNILAB)](http://unilab.edu.br/)

## Dependências:
|lib                 |Download                                                                                                 |
|--------------------|-----------------------------------------------------------------------------------------------------|
|*jsoup-1.10.3.jar*  |[Download JAR](https://jsoup.org/packages/jsoup-1.10.3.jar)                                          |
|*gson-2.8.4.jar*    |[Download JAR](http://central.maven.org/maven2/com/google/code/gson/gson/2.8.4/gson-2.8.4.jar)                |

## Inicializar:

```java
Sistema sistema = new Sistema("UsuarioSIG","SenhaSIG");
```
## Login no SIGAA:

```java
   UsuarioSIGAA usuarioSigaa;
        try {
            usuarioSigaa = sistema.logarSIGAA();
            System.out.println(usuarioSigaa.toString());
        } catch (IOException | UsuarioSenhaIncorretosException | ErroDesconhecidoLoginException ex) {
            System.err.println(ex);
        } 
```

## Login no Pergamum:

```java
 UsuarioBiblioteca usuarioPergamum;
        try {
            usuarioPergamum = sistema.logar();
            System.out.println(usuarioPergamum.toString());
        } catch (UsuarioSenhaIncorretosException | IOException | ErroDesconhecidoLoginException ex) {
            System.err.println(ex);
        }
```
