import Swal from "sweetalert2"

class SweetAlert{
    
    missingField(){
        Swal.fire({
            title : "Empty fields Detected",
            text : "Please Fill All the fields",
            icon : 'error'
        })
    }

    successAlert(tag){
        Swal.fire({
            title : tag + " Successfully",
            icon : "success",
            timer : 1500
          })
    }

    alreadyExists(tag){
        Swal.fire({
            title : "" + tag + " already Exists",
            text : "Change the name of the " + tag ,
            icon : "warning"
        })
    }

    incorrectValues(){
        Swal.fire({
            title : "Incorrect Values Detected",
            text : "Enter Valid Numbers",
            icon : "error"
        })
    }

    deleteAlert(tag, id, aFunction){
        Swal.fire({
            title : "Delete " + tag + "?",
            text : "Are you sure You want to delete? It cannot be undone",
            icon : "warning",
            showCancelButton : true,
            cancelButtonText : "No",
            showConfirmButton : true,
            confirmButtonText : "Delete"
        }).then((result) => {
            if(result.isConfirmed){
                aFunction(id);
            }
        })
    }

    duplicateOptions(){
        Swal.fire({
            title : "Duplicate Options Found",
            icon : "error"
        })   
    }

    limitReached(){
        Swal.fire({
            title : "You have reached the limit",
            icon : 'warning',
            showConfirmButton: false,
            timer : 1500
        })
    }

    quizSubmitted(){
        Swal.fire({
            title : "Quiz submitted",
            text : "redirecting to Profile",
            timer : 1500,
            timerProgressBar : true,
            showConfirmButton : false,
            backdrop: `rgba(80,108,62,0.7)`
        })
    }

    manualQuizSubmitted(){
        Swal.fire({
            title : "Quiz submitted",
            text : "redirecting to User",
            timer : 1500,
            timerProgressBar : true,
            showConfirmButton : false,
            backdrop: `rgba(80,108,62,0.7)`
        })
    }

    registrationSuccessFireAlert(img){
        Swal.fire({
          title : "Successfully Registered",
          text : "Hurray, Now you are our subscriber.!",
          imageUrl : img,
          imageHeight : 150,
          imageWidth : 150,
        })
      }
    
      registrationFailureFireAlert(failureText) {
        Swal.fire({
          title : "Unable to Register",
          text : failureText,
          icon : "error"
        })
      }

     alertError(){
        Swal.fire({
            title : "Login Failed",
            text : "Invalid Email or Password",
            icon : "error"
        })
    }
}

export default new SweetAlert();