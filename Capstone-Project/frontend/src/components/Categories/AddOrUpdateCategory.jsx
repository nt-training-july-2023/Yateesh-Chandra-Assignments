import React, { useEffect, useState } from "react";
import "./Categories.css";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import AdminNavBar from "../AdminNavBar";

const AddOrUpdateCategory = () =>{

  const { categoryId } = useParams();
  const navigate = useNavigate();

  const isUpdating = categoryId !== undefined; // Check if categoryId exists for update mode

  const [categoryName, setCategoryName] = useState("");
  const [description, setDescription] = useState("");
  const [categoryNameError, setCategoryNameError] = useState("");
  const [categoryDescriptionError, setCategoryDescriptionError] = useState("");

  // Function to fetch category data if in update mode
  const fetchCategoryData = () => {
    axios.get(`http://localhost:8082/api/v1/category/${categoryId}`)
      .then((response) => {
        const { categoryName, description } = response.data;
        setCategoryName(categoryName);
        setDescription(description);
      })
      .catch((error) => {
        console.error("Error fetching category data: ", error);
      });
  };

  useEffect(() => {
    if (isUpdating) {
      fetchCategoryData();
    }
  }, [isUpdating]);

  const handleCategoryNameChange = (e) => {
    const validName = e.target.value;
    setCategoryName(validName);
    if (!validName) {
      setCategoryNameError("Category name is required");
    } else {
      setCategoryNameError("");
    }
  };

  const handleCategoryDescriptionChange = (e) => {
    const validDescription = e.target.value;
    setDescription(validDescription);
    if (!validDescription) {
      setCategoryDescriptionError("Category description is required");
    } else {
      setCategoryDescriptionError("");
    }
  };

  const validForm = () => {
    let isValid = true;
    if (!categoryName) {
      setCategoryNameError("Category Title is Required");
      isValid = false;
    } else {
      setCategoryNameError("");
    }

    if (!description) {
      console.log("Add some Description to proceed");
      isValid = false;
    } else {
      console.log("The description is Valid");
      setCategoryDescriptionError("");
    }
    return isValid;
  };

  const handleAddOrUpdateCategory = async (e) => {
    e.preventDefault();

    if (!validForm()) {
      return;
    }

    const categoryData = {
      categoryName,
      description,
    };

    try {
      if (isUpdating) {
        // Update category if in update mode
        await axios.put(`http://localhost:8082/api/v1/category/${categoryId}`, categoryData);
        console.log("Category updated successfully.");
      } else {
        // Add new category if in add mode
        await axios.post('http://localhost:8082/api/v1/category', categoryData);
        console.log("Category added successfully.");
      }
      navigate("/manage-category");
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <div className="App">
        <AdminNavBar/>
    <div className="add-category-container">
      <h1>{isUpdating ? 'Update Category' : 'Add Category'}</h1>
      <form onSubmit={handleAddOrUpdateCategory}>
        <div className="form-group">
          <label>Category Name:</label>
          <input
            type="text"
            value={categoryName}
            onChange={handleCategoryNameChange}
            placeholder="Enter Category Name"
          />
          <span className="error-message">{categoryNameError}</span>
        </div>
        <div className="form-group">
          <label>Description:</label>
          <textarea
            value={description}
            onChange={handleCategoryDescriptionChange}
            placeholder="Enter Category Description"
          />
          <span className="error-message">{categoryDescriptionError}</span>
        </div>
        <div className="form-group">
          <div className="button-container-category">
            <button type="submit">{isUpdating ? 'Update Category' : 'Add Category'}</button>
            <button type = "button" className="red-button" onClick={()=> navigate("/manage-category")}>Cancel</button>
          </div>
        </div>
      </form>
    </div>
    </div>
  );

}

export default AddOrUpdateCategory;