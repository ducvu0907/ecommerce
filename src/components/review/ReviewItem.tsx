import React from 'react';
import { Avatar, AvatarImage } from "@/components/ui/avatar";
import { Star, ThumbsUp } from "lucide-react";
import { Button } from "@/components/ui/button";
import { formatDate } from '@/helpers';
import { ReviewData } from '@/types/models';
import { getUserProfile } from '@/services/user';
import { Skeleton } from '../ui/skeleton';

interface ReviewItemProps {
  review: ReviewData
}
const ReviewItemSkeleton: React.FC = () => {
  return (
    <div className="p-4 border-b">

      <div className="flex items-center space-x-3 mb-2">
        <Skeleton className="h-10 w-10 rounded-full" />
        <div className="flex flex-col space-y-1">
          <Skeleton className="h-4 w-24" />
          <Skeleton className="h-3 w-16" />
        </div>
      </div>

      <div className="flex space-x-1 mb-3">
        {Array.from({ length: 5 }).map((_, index) => (
          <Skeleton key={index} className="h-4 w-4 rounded" />
        ))}
      </div>

      <div className="mb-3">
        <Skeleton className="h-3 w-full" />
        <Skeleton className="h-3 w-5/6 mt-1" />
      </div>

      <div className="flex justify-between items-center">
        <Skeleton className="h-3 w-20" />
        <Skeleton className="h-6 w-16 rounded" />
      </div>

    </div>
  );
};

const ReviewItem: React.FC<ReviewItemProps> = ({ review }) => {
  const {data: user, isLoading} = getUserProfile(review.userId);
  const renderStars = (rating: number) => {
    return Array.from({ length: 5 }).map((_, index) => (
      <Star
        key={index}
        className={`h-4 w-4 ${index < rating ? 'text-yellow-500 fill-yellow-500' : 'text-gray-300'}`}
      />
    ));
  };

  if (isLoading) {
    return <ReviewItemSkeleton />;
  }

  return (
    <div className="p-4 border-b">
      <div className="flex items-center space-x-3 mb-2">
        <Avatar className="h-10 w-10">
          <AvatarImage src={`https://i.pravatar.cc/300`} />
        </Avatar>
        <div>
          <div className="flex items-center space-x-2">
            <span className="font-semibold text-sm">
              {user?.result?.firstName} {user?.result?.lastName}
            </span>
            <div className="flex">
              {renderStars(review.rating)}
            </div>
          </div>
          <span className="text-xs text-gray-500">
            {formatDate(review.createdAt)}
          </span>
        </div>
      </div>

      <div className="mb-3">
        <p className="text-sm text-gray-800">{review.content}</p>
      </div>

      <div className="flex justify-between items-center">
        <span className="text-xs text-green-600 font-medium">
          Verified Purchase
        </span>
        <Button variant="ghost" size="sm" className="text-gray-500 hover:text-blue-600">
          <ThumbsUp className="h-4 w-4 mr-1" />
          Helpful
        </Button>
      </div>
    </div>
  );
};

export default ReviewItem;