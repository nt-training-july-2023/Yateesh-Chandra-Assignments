import React from "react";

const IconLeftButton = ({ text, icon, onClick, className }) => {
  return (
    <div>
      <button className={className} onClick={onClick}>
        {icon && icon} {text}
      </button>
    </div>
  );
};

export default IconLeftButton;
