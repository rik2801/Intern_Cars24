import react from 'react'
import icons  from 'fluent-icons-react'


export const Icons = {
    add : icons.AddFilled
}

/*
Contains button with image and text over it
icon = icon type 
text = display text
onClick = event occur on click
alt = alternative for image
*/
export class ImageButton extends react.Component
{
    constructor(props)
    {
        super();
        this.props = props;
    }

    render()
    {
        return(
            <div>
                
            </div>
        )
    }
}