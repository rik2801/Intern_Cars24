package com.cg.cars.exception;

public class AppoitnmentNotFoundException extends AppointmentExceptions{

    public AppoitnmentNotFoundException(String message) {
        super(message);
        this.message=message;
        //TODO Auto-generated constructor stub
    }

    private final String message;

    @Override
    public String toString()
    {
        StringBuilder exceptionMessageBuilder=new StringBuilder();
        exceptionMessageBuilder.append(message+"\n");
        exceptionMessageBuilder.append("Appointment not found exception occured at \n");
        for(StackTraceElement element: this.getStackTrace())
        {
            exceptionMessageBuilder.append(element.toString()+"\n");
        }
        return exceptionMessageBuilder.toString();
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Override
    public String getMessage()
    {
        return this.message;
    }
    
}
