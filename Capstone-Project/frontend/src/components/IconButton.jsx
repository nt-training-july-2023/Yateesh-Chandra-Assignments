import React from "react";

const IconButton = ({ text, icon, onClick, className }) => {
  return (
    <div>
      <button className={className} onClick={onClick}>
        {text} {icon && icon}
      </button>
    </div>
  );
};

export default IconButton;
