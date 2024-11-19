

interface InputProps{
    nameLabel: string; 
    namePlaceholder: string; 
    setValue: (value: string) => void;
    type: string
}

const Input = ({ nameLabel, namePlaceholder, setValue, type} : InputProps) => {
  return (
    <div>
      <label className="flex flex-col">{nameLabel}
          <input className="w-64"
          type={type} placeholder={namePlaceholder} onChange={(e) => setValue(e.target.value)}/>
      </label>
    </div>
  )
}

export default Input
