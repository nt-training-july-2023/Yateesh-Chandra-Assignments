import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Categories.css';
import AdminNavBar from '../AdminNavBar';
import Swal from 'sweetalert2';
import UserNavBar from '../UserNavBar';

const ManageCategory = () => {
  const [categories, setCategories] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');
  const navigate = useNavigate();
  const userRole = localStorage.getItem("role");

  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = () => {
    axios.get("http://localhost:8082/api/v1/category")
      .then((response) => setCategories(response.data))
      .catch((error) => console.log("Error fetching Categories", error));
  };

  const handleDeleteCategory = (categoryId) => {
    axios
      .delete(`http://localhost:8082/api/v1/category/${categoryId}`)
      .then(() => {
        console.log("Category deleted : ", categoryId);
        fetchCategories();
      })
      .catch((error) => console.error("Error deleting category : ", error));
  };

  const handleEditClick = (categoryId) => {
    navigate(`/add-category/${categoryId}`);
  };

const handleOpenButton = (categoryId, categoryName) => {
  {localStorage.setItem("categoryName",categoryName)}
  navigate(`/manage-quiz/${categoryId}`);
}

  const filterCategory = categories.filter((category) => {
    return category.categoryName.toLowerCase().includes(searchQuery.toLowerCase());
  });

  return (
    <div className='App'>
        {userRole === "ADMIN" ? <AdminNavBar/> : <UserNavBar/>}
        
    <div className="manage-category-container">
      <h1>Category List</h1>
      <div className='button-search-container'>
      {userRole === "ADMIN"&&(
      <button className="add-category-button" onClick={() => navigate('/add-category')}>
        Add Category
      </button>
      )}
      <div className='search-bar'>
        <input 
        type = "text"
        placeholder='Search'
        value={searchQuery}
        onChange={(e) => setSearchQuery(e.target.value)}
        />
        
      </div>
    </div>
      <div className="category-table-container">
        <table className="category-table">
          <thead>
            <tr>
              <th>Category Name</th>
              <th>Description</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {filterCategory.map((category) => (
              <tr key={category.categoryId}>
                <td>{category.categoryName}</td>
                <td>{category.description}</td>
                
                <td>
                  {userRole === "ADMIN" && (    
                  <button className="blue-button" onClick={() => handleEditClick(category.categoryId)}>Update</button>
                  )}    
                  {userRole === "ADMIN" && (    
                  <button className="red-button" onClick={() => 
                    Swal.fire({
                      title : "Delete Category?",
                      text : "Are you sure You want to delete. It cannot be undone",
                      icon : "warning",
                      showCancelButton : true,
                      cancelButtonText : "No",
                      showConfirmButton : true,
                      confirmButtonText : "Delete"
                    }).then((result) => {
                      if(result.isConfirmed){
                        handleDeleteCategory(category.categoryId)    
                      }
                    })
                    }>Delete</button>
                  )}    
                  <button className="green-button" onClick={() => handleOpenButton(category.categoryId, category.categoryName)}>Open</button>
                </td>
                
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
    </div>
  );
}

export default ManageCategory;
