import { useContext, useState } from 'react';
import { Button } from "@/components/ui/button";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog";
import { Textarea } from "@/components/ui/textarea";
import { Star, Edit } from "lucide-react";
import { createReviewMutation } from '@/services/review';
import { validateReviewForm } from '@/helpers';
import { AuthContext } from '@/contexts/AuthContext';
import { toast } from '@/hooks/use-toast';

interface CreateReviewFormProps {
  productId: string;
};

const CreateReviewForm: React.FC<CreateReviewFormProps> = ({productId}) => {
  const { token } = useContext(AuthContext);
  const [rating, setRating] = useState<number>(0);
  const [content, setContent] = useState<string>('');
  const { mutate: createReview, isPending } = createReviewMutation();
  const [isDialogOpen, setIsDialogOpen] = useState<boolean>(false);

  const handleCreateReview = async () => {
    try {
      if (!validateReviewForm(rating, content)) {
        toast({
          title: "Invalid review",
          variant: "destructive"
        });
        return;
      }

      if (!token) {
        toast({
          title: "Log in to submit your review",
          variant: "destructive"
        });
        return;
      }

      createReview({ request: { productId, rating, content } });

    } finally {
      setIsDialogOpen(false);
    }
  };

  return (
    <Dialog open={isDialogOpen || isPending} onOpenChange={setIsDialogOpen}>
      <DialogTrigger asChild>
        <Button variant="outline" className="w-full flex items-center justify-center space-x-2">
          <Edit className="h-5 w-5" />
          <span>Write a review</span>
        </Button>
      </DialogTrigger>
      <DialogContent aria-describedby={"review-dialog"} className="sm:max-w-[500px]">
        <DialogHeader>
          <DialogTitle>Write Your Review</DialogTitle>
        </DialogHeader>

        <div className="space-y-4">
          <div className="flex justify-center items-center space-x-2">
            {[1, 2, 3, 4, 5].map((starRating) => (
              <Star
                key={starRating}
                className={`h-8 w-8 cursor-pointer ${starRating <= rating
                    ? 'text-yellow-500 fill-current'
                    : 'text-gray-300'
                  }`}
                onClick={() => setRating(starRating)}
              />
            ))}
          </div>

          <div className="text-center text-sm text-gray-500">
            {rating === 0
              ? 'Select your rating'
              : `You selected ${rating} star${rating !== 1 ? 's' : ''}`}
          </div>

          <Textarea
            placeholder="Write your review here"
            value={content}
            onChange={(e) => setContent(e.target.value)}
            className="min-h-[150px]"
          />

          <Button
            onClick={handleCreateReview}
            disabled={isPending}
            className="w-full"
          >
            {isPending ? 'Submitting...' : 'Submit Review'}
          </Button>
        </div>
      </DialogContent>
    </Dialog>
  );
};

export default CreateReviewForm;
