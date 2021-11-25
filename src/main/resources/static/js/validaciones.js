
function verContraseña() {
    let password = document.getElementById("password");
    let c_password;
    if (document.title == "Registrar Usuario") {
        c_password = document.getElementById("c-password");
    }
    let eye = document.getElementById("password-eye");
    if (password.type == "password") {
        eye.setAttribute("src", "/img/icons/eye-regular.svg");
        password.setAttribute("type", "text");
        if (password !== undefined) {
            c_password.setAttribute("type", "text");
        }
    } else {
        eye.setAttribute("src", "/img/icons/eye-slash-regular.svg");
        password.setAttribute("type", "password");
        c_password.setAttribute("type", "password");
    }
}

function registrarAction() {
    let inputs = document.querySelectorAll(".input");
    let check1 = document.getElementById("check_admin");
    let check2 = document.getElementById("check_supervisor");
    let check3 = document.getElementById("check_interventor");
    if (inputs[0].value !== "" && inputs[1].value !== "" && inputs[2].value !== "" && inputs[3].value !== "" && inputs[4].value !== "" && inputs[5].value !== "" && check1.checked || check2.checked || check3.checked) {
        if (verificarUsername(inputs[2].value)){
            if (verificarContraseñas(inputs)) {
<<<<<<< HEAD
                subirArchivo();
=======
                document.formulario.submit();
>>>>>>> 1bae8229b8a1692188726e309da0f103fc3285b6
            }
        }else{
            alert("El usuario no es correo valido.");
        }  
    } else {
        alert("Por favor, complete todos los campos.");
    }
}

function verificarUsername(username) {
    let codigo;
    for (let index = 0; index < username.length; index++) {
        codigo = username.charCodeAt(index);
        //@
        if (codigo == 64) {
            return true;
        }
    }
    return false;
}

    function verificarContraseñas(inputs) {
        if (inputs[3].value == inputs[4].value) {
            let password = inputs[3].value;
            if (password.length == 8) {
                let codigo;
                let mayus, mini, sim;
                mayus = false;
                mini = false;
                num = false;
                for (let index = 0; index < password.length; index++) {
                    codigo = password.charCodeAt(index);
                    //Mayusculas
                    if (codigo >= 65 && codigo <= 90) {
                        mayus = true;
                    }
                    //Minusculas
                    if (codigo >= 97 && codigo <= 122) {
                        mini = true;
                    }
                    //Numeros
                    if (codigo >= 48 && codigo <= 57) {
                        num = true;
                    }
                }
                if (mayus && mini && num) {
                    return true;
                }
            } else {
                alert("La contraseña debe tener como minimo y maximo 8 caracteres. \n Además, la contraseña debe tener al menos una letra mayuscula, una minuscula y un numero.");
                return false;
            }
        } else {
<<<<<<< HEAD
            alert("Las contraseñas no coinciden.");
            return false;
        }
}

/*const img_nav = document.getElementById("img_nav");
img_nav.src=abrirArchivo(img_nav.getAttribute("src"));

const img_menu = document.getElementById("img_menu");
img_menu.src=abrirArchivo(img_menu.getAttribute("src"));

if(document.title==="Sistema Administrador Principal"){
    const img_main = document.getElementById("user_img");
    img_main.src=abrirArchivo(img_main.getAttribute("src"));
}
if(document.title==="Sistema Interventor Principal"){
    const img_main = document.getElementById("user_img");
    img_main.src=abrirArchivo(img_main.getAttribute("src"));
}
if(document.title==="Sistema Supervisor Principal"){
    const img_main = document.getElementById("user_img");
    img_main.src=abrirArchivo(img_main.getAttribute("src"));
}*/

=======
            alert("Las contraseñas no coinciden.")
            return false;
        }
}
>>>>>>> 1bae8229b8a1692188726e309da0f103fc3285b6
