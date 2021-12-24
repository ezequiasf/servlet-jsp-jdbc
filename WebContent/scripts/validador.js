/**
 * @author Ezequias Barros
 * 
 */
function validar() {
    let nome = document.getElementById('nome_contato')
    let telefone = document.getElementById('telefone')
    let email = document.getElementById('email_contato')
    
    if (nome.value == "") {
        alert('O Campo nome deve ser preenchido')
        nome.focus();
        return false;
    }
    else if (telefone.value == "") {
        alert('O Campo telefone deve ser preenchido')
        telefone.focus();
        return false;
    }
    else if (email.value == "") {
        alert('O Campo email deve ser preenchido')
        email.focus();
        return false;
    }else{
        document.forms["formContato"].submit();
    }
}

