import Swal from "sweetalert2"

class SweetAlert{
    
    missingField(){
        Swal.fire({
            title : "Empty fields Detected",
            text : "Please Fill All the details",
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

}

export default new SweetAlert();