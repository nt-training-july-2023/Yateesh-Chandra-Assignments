import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Categories.css';
import AdminNavBar from '../../components/NavBars/AdminNavBar';
import { FaExternalLinkAlt, FaPen, FaPlusCircle, FaTrash } from 'react-icons/fa';
import DeactivateBackButton from '../../components/DeactivateBackButton';
import NotFound from '../../pages/HomePage/NotFound';
import CategoryService from '../../services/CategoryService';
import SweetAlert from '../../components/SweetAlerts/SweetAlert';
import IconButton from '../../components/IconButton';
import IconLeftButton from '../../components/IconLeftButton';
import Header1 from '../../components/Header1';

const ManageCategory = () => {
    const [categories, setCategories] = useState([]);
    const [searchQuery, setSearchQuery] = useState('');
    const navigate = useNavigate();
    const userRole = localStorage.getItem("role");

    useEffect(() => {
        getCategories();
    }, []);

    const getCategories = () => {
        CategoryService.getCategories()
        .then((response) => setCategories(response.data.body))
        .catch((error) => console.log("Error fetching Categories", error));
    };

    const handleDeleteCategory = (categoryId) => {
        CategoryService.deleteCategory(categoryId)
        .then(() => {
            getCategories();
        })
    };

    const handleEditClick = (categoryId) => {
        navigate(`/add-category/${categoryId}`);
    };

    const handleDeleteClick = (categoryId) => {
        SweetAlert.deleteAlert("Category", categoryId, handleDeleteCategory);
    };

    const handleOpenButton = (categoryId, categoryName) => {
        {localStorage.setItem("categoryId", categoryId)}
        {localStorage.setItem("categoryName",categoryName)}
        navigate(`/manage-quiz/${categoryId}`);
    };

    const filterCategory = categories.filter((category) => {
        return category.categoryName.toLowerCase().includes(searchQuery.toLowerCase());
    });

    return (
        <div className='App'>
            <DeactivateBackButton/>
            {userRole === "ADMIN" || userRole === "USER" ? (
                <>
                    {userRole === "ADMIN" && <AdminNavBar/>}
                    <div className="manage-category-container">
                        <Header1 text = {filterCategory.length > 0 ? userRole === "ADMIN" && ("Category List") : "No Category"} className = "arial"/>
                        <div className='button-search-container'>
                            {userRole === "ADMIN" && (
                                <IconButton className="blue-button button" onClick={() => navigate('/add-category')} text="Add " icon={<FaPlusCircle/>} />
                            )}
                            <div className='search-bar'>
                                <input
                                    type="text"
                                    className='search-input'
                                    placeholder='Search Category'
                                    value={searchQuery}
                                    onChange={(e) => setSearchQuery(e.target.value)}
                                />
                            </div>
                        </div>
                        {filterCategory.length > 0 ? (
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
                                                    <div className='button-container-category'>
                                                    {userRole === "ADMIN" && (    
                                                        <IconLeftButton text = "Update" className="blue-button button-category" onClick={() => handleEditClick(category.categoryId)} icon = {<FaPen className="very-small-icon" />}/>
                                                    )}    
                                                    {userRole === "ADMIN" && (    
                                                        <IconLeftButton text = "Delete" className="red-button button-category" onClick={() => handleDeleteClick(category.categoryId)} icon = {<FaTrash className="very-small-icon" />}/>
                                                    )}    
                                                       <IconLeftButton
                                                        text={userRole === "ADMIN" ? "Open" : "View Quiz"}
                                                        className={userRole === "ADMIN" ? "green-button button-category" : "green-button button-wide"}
                                                        onClick={() => handleOpenButton(category.categoryId, category.categoryName)}
                                                        icon={<FaExternalLinkAlt className="very-small-icon" />}
                                                        />

                                                    </div>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        ) : (
                            <></>
                        )}
                    </div>
                </>
            ) : (
                <div>
                    <NotFound/>
                </div>
            )}
        </div>
    );
    
}

export default ManageCategory;
