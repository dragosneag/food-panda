import React from 'react'

const Zone = ({ zone, onClick }) => {
    
    return (
        <div className = "speciality" onClick={onClick}>
            <h3>
                {zone.name}
            </h3>
        </div>
    )
}

export default Zone