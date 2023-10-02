import React from "react";

const ButtonComponent = (props) => {

    return (
        <div>
            <button
                onClick={props.onClick}
                type={props.type}
                className={props.className}
            />
        </div>
    );
}

export default ButtonComponent;