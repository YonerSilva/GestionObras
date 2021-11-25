async function subirArchivo() {
    inputFile = document.getElementById("formFile");
    if (inputFile.files.length === 0) {
        alert("Por favor seleciona un archivo");
        return;
    } else {
        let file = inputFile.files[0];
        let storageRef = firebase.storage().ref("Imagenes");
        const fileName = file.name.toString();
        let path = modificarName(fileName);
        let thisRef = storageRef.child(path);
        const task = thisRef.put(file);
        firebase.auth().signInAnonymously()
                .then(function () {
                    task
                    .then(snapshot => snapshot.ref.getDownloadURL())
                    .then(url => {
                        document.getElementById("foto").value = url;
                        document.formulario.submit();  
                    });

                }).catch(function (error) {
            var errorCode = error.code;
            var errorMessage = error.message;
            console.log(errorCode);
            console.log(errorMessage);
        });
    }
}

function modificarName(fileName) {
    const nombreUsuario = document.getElementById("nombre").value;
    let nombres = nombreUsuario.toString().split(" ");
    let username = document.getElementById("username").value;
    return hashCode(username) + "-" + nombres[0] + getExtension(fileName);
}

function getExtension(fileName) {
    const file = fileName;
    let extension = "";
    let char = "";
    for (let i = file.length; i > -1; i--) {
        char = file.toString().charAt(i);
        extension += char;
        if (char === ".")
            i = 0;
    }
    let cadena = "";
    for (let i = extension.length; i > -1; i--) {
        cadena += extension.charAt(i);
    }
    return cadena;
}

function hashCode(s) {
    let h = 0;
    for (let i = 0; i < s.length; i++)
        h = Math.imul(31, h) + s.charCodeAt(i) | 0;

    return Math.abs(h);
}