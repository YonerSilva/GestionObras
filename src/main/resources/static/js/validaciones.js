function verContraseña(){
    let password = document.getElementById("password");
    let c_password;
    if(document.title=="Registrar Usuario"){
        c_password = document.getElementById("c-password");
    }
    let eye = document.getElementById("password-eye");
    if(password.type == "password"){
        eye.setAttribute("src", "/img/icons/eye-regular.svg");
        password.setAttribute("type","text");
        if(password !== undefined){
            c_password.setAttribute("type","text");
        }
    }else{
        eye.setAttribute("src", "/img/icons/eye-slash-regular.svg");
        password.setAttribute("type","password");
        c_password.setAttribute("type","password");
    }
}

function registrarAction(){
    let inputs = document.querySelectorAll(".input");
    console.log(inputs);
    let check1 = document.getElementById("check_admin");
    let check2 = document.getElementById("check_supervisor");
    let check3 = document.getElementById("check_interventor");
    if(inputs[0].value!=""&&inputs[1].value!=""&&inputs[2].value!=""&&inputs[3].value!=""&&inputs[4].value!=""&&inputs[5].value!=""&&check1.checked || check2.checked || check3.checked){
        if(verificarContraseñas(inputs)){
            alert("Usuario Registrado Correctamente, por favor verificar correo electronico.");
            document.location.href = "../index.html";
        }
    }else{
        alert("Por favor, complete todos los campos.");
    }
}

function verificarContraseñas(inputs){
    if(inputs[3].value == inputs[4].value){
        let password = inputs[3].value;
        if(password.length == 8){
            let codigo;
            let mayus, mini, sim;
            mayus = false;
            mini = false;
            num = false;
            for (let index = 0; index < password.length; index++) {
                codigo = password.charCodeAt(index);
                //Mayusculas
                if(codigo >= 65 && codigo <= 90){
                    mayus = true;
                }
                //Minusculas
                if(codigo >= 97 && codigo <= 122){
                    mini = true;
                }
                //Numeros
                if(codigo >= 48 && codigo <= 57){
                    num = true;
                }
            }
            if(mayus && mini && num){
                return true;
            }
        }else{
            alert("La contraseña debe tener como minimo y maximo 8 caracteres. \n Además, la contraseña debe tener al menos una letra mayuscula, una minuscula y un numero.");
            return false;
        }
    }else{
        alert("Las contraseñas no coinciden.")
        return false;
    }
}