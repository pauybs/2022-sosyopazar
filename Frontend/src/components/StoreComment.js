import { Alert, AlertTitle } from '@mui/material';
import { useSnackbar } from 'notistack';
import React, { useEffect, useState } from 'react'
import { getSellerCommentsRequest } from '../api/controllers/seller-comment-controller';
import CommentCard from './CommentCard';

const StoreComment = ({ sellerId }) => {
    const { enqueueSnackbar } = useSnackbar();

    //GET COMMENTS
    const [commentList, setCommentList] = useState([])
    const fetchCommentList = async () => {
        try {
            let res = await getSellerCommentsRequest(sellerId);
            if (res) {
                setCommentList(res.data);
            }
        } catch (error) {
            if (error.response.status === 401) {
                enqueueSnackbar('Lütfen giriş yapın!', { variant: 'error' });
            } else if (error.response.status === 403) {
                enqueueSnackbar('Bu işlem için yetkiniz bulunmuyor!', { variant: 'error' });
            } else {
                enqueueSnackbar('Bir hata meydana geldi. Daha sonra tekrar deneyin!', { variant: 'error' });
            }
        }
    }

    useEffect(() => {
        fetchCommentList()
    }, [])

    return (
        <>
            {
                commentList.length > 0 ?
                    commentList.map((val, i) =>

                        <CommentCard key={i} commentData={val} />

                    ) :
                    <Alert severity="warning">
                        <AlertTitle>Henüz yorum yapılmamış :(</AlertTitle>
                        Bu mağazaya henüz yorum yapılmamış.
                    </Alert>
            }
        </>
    )
}

export default StoreComment;