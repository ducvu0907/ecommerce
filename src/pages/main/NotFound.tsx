import { Button } from "@/components/ui/button";
import { useNavigate } from "react-router-dom";

const NotFound = () => {
  const navigate = useNavigate();

  return (
    <div className="w-full h-screen flex flex-col items-center justify-center p-6 text-center">
      <h1 className="text-6xl font-extrabold text-red-500">404</h1>
      <p className="mt-4 text-lg text-gray-700">
        Oops! The page you're looking for doesn't exist.
      </p>
      <Button
        className="mt-6"
        onClick={() => navigate("/")}
        variant="default"
      >
        Go to Homepage
      </Button>
    </div>
  );
};

export default NotFound;
