import Swal from "sweetalert2"

class SweetAlert{
    
    missingField(){
        Swal.fire({
            title : "Empty fields Detected",
            text : "Please Fill All the fields",
            icon : 'error'
        })
    }

    addSuccessAlert(){
        Swal.fire({
            title : "Added Successfully",
            icon : "success",
            timer : 1500
          })
    }

    updateSuccessAlert(){
        Swal.fire({
            title : "Updated Successfully",
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
            timer : 1500
        })
    }
}

export default new SweetAlert();