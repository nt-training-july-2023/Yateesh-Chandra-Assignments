import React from "react";

const InputComponent = (props) => {

    return(
        <div>
            <input 
            className={props.className}
            type = {props.type}
            name = {props.name}
            id = {props.id}
            placeholder = {props.placeholder}
            value={props.value}
            onChange={props.onChange}
            defaultValue={props.defaultValue}
            checked = {props.checked}
            onClick={props.onClick}
            />
        </div>
    );
}

export default InputComponent;