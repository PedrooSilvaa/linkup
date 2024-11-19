import React from 'react'

interface ButtonProps{
    type: string;
    text: string;
    fun: () => void;
}

const Button = ({ type, text, fun } : ButtonProps) => {
  return (
    <div>
        {type == "submit" ? (
                <button className='bg-black text-white' onClick={() => fun()}>{text}</button>
            ) 
            :
            (
                <button onClick={() => fun()}>{text}</button>
            )
        }
    </div>
  )
}

export default Button
