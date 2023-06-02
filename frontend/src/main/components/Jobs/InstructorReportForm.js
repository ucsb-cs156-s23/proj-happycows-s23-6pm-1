import { Button, Form } from "react-bootstrap";
import { useForm } from "react-hook-form";

function InstructorReportForm() {
    // Stryker disable all
    const {
      handleSubmit,
    } = useForm(
    );
    // Stryker enable all

    return (
      <Form onSubmit={handleSubmit(submitAction)}>
        <p>Click this button to generate an instructor report!</p>
        <Button type="submit" data-testid="InstructorReport-Submit-Button">Update</Button>
    </Form>
    );
  }
  
  export default InstructorReportForm;
  
