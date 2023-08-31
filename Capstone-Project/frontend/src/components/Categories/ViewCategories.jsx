import axios from "axios";
import React, { useEffect, useState } from "react";

const ViewCategories = () => {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        fetchCategories();
    }, []);

    const fetchCategories = () => {
        axios.get('http://localhost:8082/api/v1/category')
        .then(response => setCategories(response.data))
        .catch(error => console.error('Error fetching categories : ', error));
    };

    return (
        <div className = "wrapper">
            <h1> View categories</h1>
            <ul>
                {categories.map(category => (
                    <li key = {category.categoryId}>{category.categoryName}</li>
                ))}
            </ul>
        </div>
    );
};

export default ViewCategories;
