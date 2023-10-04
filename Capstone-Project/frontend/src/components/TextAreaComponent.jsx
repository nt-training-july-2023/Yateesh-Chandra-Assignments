import React from "react";

const TextAreaComponent = (props) => {

    return(
        <div>
            <textarea
            className={props.className}
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

export default TextAreaComponent;